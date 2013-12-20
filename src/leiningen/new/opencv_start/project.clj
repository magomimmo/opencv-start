(defproject {{raw-name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "bsd 3-clause license"
            :url "http://opensource.org/licenses/BSD-3-Clause"}

  ;; override the default "srs" source-paths
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 ;; opencv/opencv artifact has to be installed in the
                 ;; local maven repository
                 [opencv/opencv "2.4.7"]
                 ;; opencv/opencv-native artifact has to be installed
                 ;; in the local maven repository
                 [opencv/opencv-native "2.4.7"]
                 ;; seesaw is used to create windows and to show
                 ;; images in them
                 [seesaw "1.4.4"]
                 ;; fs is used to simplify file system stuff
                 [me.raynes/fs "1.4.4"]]
  ;; it loads the native opencv lib
  :injections [(clojure.lang.RT/loadLibrary org.opencv.core.Core/NATIVE_LIBRARY_NAME)])
