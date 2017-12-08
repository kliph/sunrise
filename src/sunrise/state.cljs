(ns sunrise.state
  (:require [reagent.core :as reagent]))

(def initial-state
  {:window nil
   :colors {:horizon "#e09d83"
            :zenith "#3e4962"
            :sun-center "white"
            :sun-rim "#ffc10a"}})

(def display-state (reagent/atom initial-state))

(defn update-window! [dimensions]
   (swap! display-state assoc :window dimensions))
