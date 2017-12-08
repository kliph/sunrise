(ns sunrise.sun
  (:require [sunrise.state :as state]))

(defn sun []
  (let [colors (@state/display-state :colors)]
    [:circle.sun {:r "10%"
                  :cx "40%"
                  :fill (colors :sun-center)
                  :cy "25%"}]))
