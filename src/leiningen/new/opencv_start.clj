(ns leiningen.new.opencv-start
  (:require [leiningen.new.templates :refer [renderer
                                             year
                                             name-to-path
                                             sanitize-ns
                                             multi-segment
                                             project-name
                                             ->files]]
            [leiningen.core.main :as main]))

(defn opencv-start
  "A general project template for OpenCV

Accept a group-id in the project name: lein new opencv-start foo.bar/baz"
  [name]
  (let [render (renderer "opencv-start")
        main-ns (multi-segment (sanitize-ns name))
        data {:raw-name name
              :name (project-name name)
              :namespace main-ns
              :nested-dirs (name-to-path main-ns)
              :year (year)}]
    (main/info "Generating fresh 'lein new' opencv-start project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["profiles.clj" (render "profiles.clj" data)]
             ["README.md" (render "README.md" data)]
             ["doc/intro.md" (render "intro.md" data)]
             [".gitignore" (render "gitignore" data)]
             ["src/clj/{{nested-dirs}}.clj" (render "core.clj" data)]
             ["test/clj/{{nested-dirs}}_test.clj" (render "test.clj" data)]
             ["LICENSE" (render "LICENSE" data)]
             "resources/images")))
