(ns leiningen.new.opencv-start
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "opencv-start"))

(defn opencv-start
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' opencv-start project.")
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
