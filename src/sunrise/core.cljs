(ns sunrise.core
  (:require [reagent.core :as r]
            [goog.dom]))

(def by-id goog.dom.getElement)

(defn app-container []
  [:svg {:xmlns "http://www.w3.org/2000/svg"
         :width "100%"
         :height "100%"}
   [:defs {}
    [:linearGradient#skyGradient {:x1 "0"
                                  :y1 "0"
                                  :x2 "0"
                                  :y2 "1"}
     [:stop {:className "stop1"
             :offset "0%"
             :stop-opacity "0"}]
     [:stop {:className "stop2"
             :offset "100%"}]
     ]]
   [:g.skybox {}
    [:rect {:width "100%"
            :className "sky"
            :height "50%"
            :y "0"
            :x "0"}]]])

(r/render-component [app-container] (by-id "app"))
