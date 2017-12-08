(ns sunrise.sky
  (:require [sunrise.state :as state]))

(defn svg-defs [colors]
  [:defs {}
   [:linearGradient#skyGradient
    {:x1 "0" :y1 "0"
     :x2 "0" :y2 "1"}

    [:stop {:className "stop1"
            :offset "0%"
            :stop-color (colors :zenith)}]
    [:stop {:className "stop2"
            :stop-color (colors :horizon)
            :offset "50%"}]]])

(defn sky []
  (let [colors (@state/display-state :colors)]
    [:g {}
     (svg-defs colors)
     [:rect.sky {:width "100%"
                 :height "100%"
                 :fill "url(#skyGradient)"
                 :y "0"
                 :x "0"}]]))
