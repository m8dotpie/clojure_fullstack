(ns atest.web
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.util.response :as rres]
            [clojure.string :as str]
            [hiccup.form :as form]
            [hiccup.page :as page]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.anti-forgery :as anti-forgery]))

(defn common [title & body]
  (h/html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
    [:meta {:name "viewport" :content
            "width=device-width, initial-scale=1, maximum-scale=1"}]
    [:title title]
    (h/include-css "/stylesheets/base.css"
                   "/stylesheets/skeleton.css"
                   "/stylesheets/screen.css")
    (h/include-css "http://fonts.googleapis.com/css?family=Sigmar+One&v1")]
   [:body
    [:div {:id "header"}
     [:h1 {:class "container"} "SHOUTER"]]
    [:div {:id "content" :class "container"} body]]))
(defn myform []
  [:div {:id "Test form" :class "test too"}
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "shout" "What do you want to SHOUT?")
                 (form/text-field "shout")
                 (form/submit-button "SHOUT!"))])
(defn index []
  (common "Main page" [:div {:id "Hello, World!"} (myform)]))

(defn index1 []
  (common "Not a main page" [:div {:id "Hello, not World!"} "Hello, not World!"]))

(defn four-oh-four []
  (common "Page Not Found"
          [:div {:id "four-oh-four"}
           "The page you requested could not be found"]))
(defn create
  [shout]
  (when-not (str/blank? shout)
    ((println "------------------------")
     (println "HERE: " (or shout "nothing"))
     (println "------------------------")))
  (rres/redirect "/"))

(defroutes routes
  (GET "/" [] (index))
  (GET "/under" [] (index1))
  (POST "/" [text] (create text))
  (route/not-found (four-oh-four)))

(def application (wrap-defaults routes site-defaults))

(defn -main []
  (ring/run-jetty application {:port 8080 :join? false}))
