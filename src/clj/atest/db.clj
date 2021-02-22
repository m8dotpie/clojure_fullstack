(ns atest.db
  (:require [clojure.java.jdbc :as jdbc]))

(def patients-sql (jdbc/create-table-ddl :patients [[:uuid "int" "PRIMARY KEY"]
                                                    [:first_name "VARCHAR(30)"]
                                                    [:last_name "VARCHAR(30)"]
                                                    [:patronymic_name "VARCHAR(30)"]
                                                    [:sex "VARCHAR(30)"]
                                                    [:date_of_birth "VARCHAR(15)"]
                                                    [:address "VARCHAR(15)"]
                                                    [:oms_document_id "VARCHAR(15)"]]
                                         {:conditional? true}))

(defn init-table [patients-db]
  (jdbc/execute! patients-db patients-sql))

(defn get-patient[uuid patients-db]
  (jdbc/query patients-db (str "SELECT * FROM patients WHERE uuid = " uuid)))

(defn update-patient [new-data patient-uuid patients-db]
  (let [old-data (get-patient patient-uuid patients-db)]
    (jdbc/update! patients-db :patients
                  {:first_name      (or (get new-data :first_name)      (get old-data :first_name))
                   :last_name       (or (get new-data :last_name)       (get old-data :last_name))
                   :patronymic_name (or (get new-data :patronymic_name) (get old-data :patronymic_name))
                   :sex             (or (get new-data :sex)             (get old-data :sex))
                   :date_of_birth   (or (get new-data :date_of_birth)   (get old-data :date_of_birth))
                   :address         (or (get new-data :address)         (get old-data :address))
                   :oms_document_id (or (get new-data :oms_document_id) (get old-data :oms_document_id))}
                  ["uuid = ?" patient-uuid])))

(defn create-patient [data patients-db]
  (let [max-uuid (get (nth (jdbc/query patients-db "SELECT MAX(uuid) FROM patients") 0) :max)]
    (jdbc/insert! patients-db :patients {:uuid            (+ max-uuid 1)
                                         :first_name      (get data :first_name)
                                         :last_name       (get data :last_name)
                                         :patronymic_name (get data :patronymic_name)
                                         :sex             (get data :sex)
                                         :date_of_birth   (get data :date_of_birth)
                                         :address         (get data :address)
                                         :oms_document_id (get data :oms_document_id)})))

(defn drop-table [patients-db]
  (jdbc/execute! patients-db (jdbc/drop-table-ddl "patients")))

