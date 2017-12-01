(ns sunrise.core
  (:require [reagent.core :as reagent]
            [sunrise.component :as component]
            [sunrise.state]))

(.addEventListener js/window "resize" sunrise.state/update!)

(reagent/render-component [component/app-container] (.getElementById js/document "app"))
