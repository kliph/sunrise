(ns sunrise.skybox
  (:require [sunrise.sky :as sky]
            [sunrise.sun :as sun]))

(defn svg-defs []
  [:defs {}
   [:linearGradient#skyGradient {:x1 "0"
                                 :y1 "0"
                                 :x2 "0"
                                 :y2 "1"}
    [:stop {:className "stop1"
            :offset "0%"
            :stop-opacity "0"}]
    [:stop {:className "stop2"
            :offset "60%"}]]])

(defn skybox []
  [:g.skybox {}
   (sky/sky)
   (sun/sun)])
