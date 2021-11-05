# Reloaded

Reloaded workflow base for clojure and clojurescript development using deps.edn and shadow-cljs.

To run:
```shell
clj
Clojure 1.10.3
user=> (reset)
:reloading (reloaded.server user)
Nov 04, 2021 3:33:48 PM clojure.tools.logging$eval1953$fn__1956 invoke
INFO: Server running on port 
#:reloaded.server{:server #object[clojure.lang.AFunction$1 0x43f7f48d "clojure.lang.AFunction$1@43f7f48d"]}
user=>
```

Reload server application by invoking `(reset)` at the REPL prompt.