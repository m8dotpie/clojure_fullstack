(ns atest.core
  (:require [atest.db :as db]
            [clojure.java.jdbc :as jdbc]
            [clj-postgresql.core :as pg]))

(def patients-db (pg/pool :host "localhost" :port "5432" :user "postgres" :dbname "postgres" ))

(defn -main []
  (db/drop-table patients-db)
  (db/init-table patients-db)
  (db/get-patient 3 patients-db)
  (db/create-patient (hash-map :first_name "Igor"
                               :last_name "Alentev"
                               :patronymic_name "Replacable"
                               :sex "male"
                               :date_of_birth "10.02.2002"
                               :address "12 of march"
                               :oms_document_id "1231231313")
                     patients-db)
  (db/create-patient (hash-map :first_name "Igoraaaaa"
                               :last_name "Alentev"
                               :patronymic_name "Replacable"
                               :sex "male"
                               :date_of_birth "10.02.2002"
                               :address "Innopolis"
                               :oms_document_id "1231231313")
                     patients-db)
  (db/update-patient (hash-map :patronymic_name "Viktorovich") 1 patients-db))

