import ch4.p11_groovy_config.Employee

beans {
    employee(Employee, name: 'Max Mayer', age: 23)
}

// java classes can be imported and used in groovy