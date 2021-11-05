(ns user
  (:require [clojure.tools.namespace.repl :as ns-tools]
            [reloaded.server :as server]))

(ns-tools/set-refresh-dirs "src/clj" "dev")

(defn start!
  []
  (server/start!))

(defn stop!
  []
  (server/stop!))

(defn reset
  []
  (stop!)
  (ns-tools/refresh :after 'user/start!))