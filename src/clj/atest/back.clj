(ns atest.back
  (:require [atest.db :as db]
            [clj-postgresql.core :as pg]))

(def patients-db (pg/pool :host "localhost" :port "5432" :user "postgres" :dbname "postgres" ))
