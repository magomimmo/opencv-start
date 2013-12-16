(defproject {{raw-name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "bsd 3-clause license"
            :url "http://opensource.org/licenses/BSD-3-Clause"}

  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [opencv/opencv "2.4.7"]
                 [opencv/opencv-native "2/4/7"]
                 [seesaw "1.4.4"]
                 [me.raynes/fs "1.4.4"]]
  :injections [(clojure.lang.RT/loadLibrary org.opencv.core.Core/NATIVE_LIBRARY_NAME)])
