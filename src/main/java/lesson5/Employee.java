package lesson5;

    //HOMEWORK TASK_1
public class Employee {
    String FIO;
    String position;
    String email;
    String phoneNumber;
    int salary;
    int age;

    //HOMEWORK TASK_2
    Employee(String FIO, String position, String email, String phoneNumber, int salary, int age){
        this.FIO = FIO;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }
    //HOMEWORK TASK_3
    static void printEmployeeInfo(Employee employee){
        System.out.printf("FIO: %s\n", employee.FIO);
        System.out.printf("position: %s\n", employee.position);
        System.out.printf("email: %s\n", employee.email);
        System.out.printf("phoneNumber: %s\n", employee.phoneNumber);
        System.out.printf("salary: %s\n", employee.salary);
        System.out.printf("age: %s\n", employee.age);
        System.out.println();
    }
    //HOMEWORK TASK_4
        static Employee[] employeesList = new Employee[]{
                new Employee("Prochorov Vitaliy","Department Lead","prochorov@work.org","+71231111111",100000,45),
                new Employee("Trubadurov Pavel","Deputy chief","trubadurov@work.org","+71232222222",80000,32),
                new Employee("Bardackov Ilya","Leading specialist","bardackov@work.org","+71233333333",85000,41),
                new Employee("Bardin Anatoliy","Engineer","bardin@work.org","+71234444444",65000,29),
                new Employee("Nagubin Taras","Technic","nagubin@work.org","+71235555555",35000,23)};
    //HOMEWORK TASK_5
    public static void main(String[] args) {
        ageFilterPrint(40);
    }
        static void ageFilterPrint(int ageMoreThan){
            for (int i = 0; i < employeesList.length; i++){
                if (employeesList[i].age > ageMoreThan){
                    printEmployeeInfo(employeesList[i]);
                }
            }
        }
}
