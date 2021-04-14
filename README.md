# Clojure Fullstack
Clojure fullstack with an hour (month)

## Topics covered:

- [ ] Unit tests
- [ ] Simple GUI
- [ ] Kubernetes
- [ ] CI/CD
- [x] PostgreSQL
- [x] Emacs/Vim setup

## Information Model

### Patient

- id              (Integer)
- first-name      (String)
- last-name       (String)
- patronymic-name (String)
- sex             (String)
- address         (String)
- date-of-birth   (Date)
- oms-document-id (Integer)

## Rest Points

### Data flow

/api/patients
- [x] GET    => get a list of patients
- [x] POST   => create a new patient
/api/patients/:patient-id
- [x] GET    => get single patient data
- [x] PUT    => update patient data
- [x] DELETE => remove existing patient
