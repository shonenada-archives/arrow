(ns arrow.utils.controller-helper)

(defn response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json;charset=utf-8"}
   :body data})
