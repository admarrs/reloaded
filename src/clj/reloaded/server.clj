(ns reloaded.server
  (:require [clojure.tools.logging :as log]
            [hiccup.page :refer [html5]]
            [integrant.core :as ig]
            [org.httpkit.server :refer [run-server]]
            [reitit.ring :as ring]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.reload :refer [wrap-reload]]))

(def sys (atom nil))

(def config
  {
   :ig/system
   {:reloaded.server/server
    {:reloaded/port 3000
     :reloaded/base-uri "http://localhost:3000"}
   }
  })

(defn make-handler
  [opts]
  (ring/ring-handler
   (ring/router
    [["/"      {:get {:handler (fn [req] {:status 200
                                          :body (html5 [:head
                                                        [:title "Reloaded"]]
                                                       [:body
                                                        [:h1 "Hello"]])})}}]
     ]
    {:data {:middleware [wrap-params
                         wrap-reload]}})
   (ring/routes
    (ring/create-resource-handler {:path "/"})
    (ring/create-default-handler))))

(defmethod ig/init-key ::server
  [_ {:reloaded/keys [port base-uri] :as opts}]
  (log/info (str "Server running on port "))
  (run-server (make-handler opts) {:port port}))

(defmethod ig/halt-key! ::server
  [_ s]
  (when s
    (log/info "Stopping server")
    (s :timeout 100)
    (Thread/sleep 1000)))

(defn start!
  []
  (reset! sys(ig/init (:ig/system config))))

(defn stop!
  []
  (when @sys
    (ig/halt! @sys)))