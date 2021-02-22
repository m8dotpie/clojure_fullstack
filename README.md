# Clojure Fullstack
Clojure fullstack with an hour (month)

## Topics covered:

- [ ] Unit tests
- [ ] Simple GUI
- [ ] Kubernetes
- [ ] CI/CD
- [ ] PostgreSQL
- [x] Emacs/Vim setup

## Information Model

### Patient

- id              (UUID)
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
- GET    => get a list of patients
- POST   => create a new patient
/api/patients/:patient-id
- GET    => get single patient data
- PUT    => update patient data
- DELETE => remove existing patient
