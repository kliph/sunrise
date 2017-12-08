(ns sunrise.sun
  (:require [sunrise.state :as state]))

(defn sun-rim-gradient [colors]
  [:defs {}
   [:radialGradient#sun-rim-gradient
    [:stop {:offset "40%"
            :stop-opacity "0"
            :stop-color (colors :sun-center)}]
    [:stop {:offset "60%"
            :stop-opacity ".7"
            :stop-color (colors :sun-rim)}]
    [:stop {:offset "100%"
            :stop-opacity "0"
            :stop-color (colors :sun-rim)}]
    ]])

(defn sun-center-gradient [colors]
  [:defs {}
   [:radialGradient#sun-center-gradient
    [:stop {:offset "20%"
            :stop-opacity "1"
            :stop-color (colors :sun-center)}]
    [:stop {:offset "100%"
            :stop-opacity "0"
            :stop-color (colors :sun-center)}]
    ]])


(defn sun []
  (let [colors (@state/display-state :colors)]
    [:g {}
      (sun-rim-gradient colors)
      (sun-center-gradient colors)
      [:circle.sun-center {:r "50%"
                    :cx "40%"
                    :fill "url(#sun-center-gradient)"
                    :cy "25%"}]
      [:circle.sun-rim {:r "10%"
                        :cx "41%"
                        :fill "url(#sun-rim-gradient)"
                        :cy "24%"}]]))
