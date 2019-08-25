package controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import entities.EmployeeEntity;
import model.Employee;
import service.EmployeeEJB;

@ManagedBean(name = "employeecontroller")
@SessionScoped
public class EmployeeController {
	
		@EJB
		private EmployeeEJB employeeEJB;
	
	//from form
	@ManagedProperty(value = "#{employee}")
	private Employee employee;
	
	private List<Employee> employeeList = new ArrayList<>();
	
	public List<Employee> getEmployeeList() {
		employeeList = getEmployeesDTO();
		return employeeList;
	}
	
	private List<Employee> getEmployeesDTO() {
		
		List<EmployeeEntity> employeesEntities = employeeEJB.findEmployees();
		List<Employee> employees = new ArrayList<Employee>();
		if (employeesEntities != null) {
			for (EmployeeEntity employeeEntity : employeesEntities) {
				Employee employee = new Employee(employeeEntity.getName(), employeeEntity.getSurName(), employeeEntity.getDateOfBirth());
				employees.add(employee);
			}
		}
		
		return employees;
	}
	
	public String viewEmployee() {
		return "employeeList.xhtml";
	}
	
	public String addNewEmployee() {
		employeeEJB.addNew(employee.getEntity());
		getEmployeeList();
		return "index.xhtml";
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}