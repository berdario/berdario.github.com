{:dev {
       :dependencies [[org.clojure/clojure "1.5.1"]]
       
       :plugins [[lein-cljsbuild "0.3.2"]]

       :cljsbuild {:builds
                   {:dev
                    {:source-paths ["src/brepl" "src/cljs"]
                     :compiler {:output-to "../berdario.js"
                                :optimizations :whitespace
                                :pretty-print true}}
                    
                    :prod
                    {:source-paths ["src/cljs"]
                     :compiler {:output-to "../berdario.js"
                                :optimizations :advanced
                                :pretty-print false}}}}}}

