(ns glosa.ports.database
  (:require
   [glosa.config :refer [config]]))

;; Enable adapter

(if (= (config :database) "plain") (require '[glosa.adapters.database.plain :as adapter]))

;; Functions

(defn get-comments
  "Find comments from url"
  [url]
  (adapter/get-comments url))

(defn get-email-parent
  "Get email from parent comment"
  [id]
  (adapter/get-email-parent id))

(defn add-comment
  "Add new comment"
  [parent author email message token thread]
  (adapter/add-comment parent author email message token thread))
