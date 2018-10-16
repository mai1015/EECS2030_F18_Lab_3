import com.sun.tools.hat.internal.util.ArraySorter;

import java.util.*;

/* READ THE PDF INSTRUCTION BEFORE GETTING STARTED!
 * 
 * Resources:
 * 	- Tutorial Series on Java Collections (ArrayList and HashMap):
 * 		https://www.eecs.yorku.ca/~jackie/teaching/tutorials/index.html#java_collections
 *	- Recording of lecture on implementing compareTo and using Arrays.sort(...):
 *		https://youtu.be/mDpDRLEy-7Y
 */

public class Database {
	/*
	 * Each entry in a 'departments' map contains
	 * a unique department id and its associated information object.
	 */
	HashMap<Integer, DepartmentInfo> departments;

	/*
	 * Each entry in a 'employees' map contains
	 * a unique employee id and its associated information object.
	 */
	HashMap<String, EmployeeInfo> employees;

	/**
	 * Initialize an empty database.
	 */
	public Database() {
		/* Your Task */
		departments = new HashMap<>();
		employees = new HashMap<>();
	}

	/**
	 * Add a new employee entry.
	 * @param id id of the new employee
	 * @param info information object of the new employee
	 * @throws IdAlreadyExistsExceptoin if 'id' is an existing employee id
	 */
	public void addEmployee(String id, EmployeeInfo info) throws IdAlreadyExistsExceptoin {
		/* Your Task */
		if (employees.containsKey(id)) throw new IdAlreadyExistsExceptoin("Employee with id " + id + " already exists");
		employees.put(id, info);
	}

	/**
	 * Remove an existing employee entry.
	 * @param id id of some employee
	 * @throws IdNotFoundException if 'id' is not an existing employee id
	 */
	public void removeEmployee(String id) throws IdNotFoundException {
		/* Your Task */
		if (!employees.containsKey(id)) throw new IdNotFoundException("Employee with id " + id + " does not exists");
		employees.remove(id);
	}

	/**
	 * Add a new department entry.
	 * @param id id of the new department
	 * @param info information object of the new department
	 * @throws IdAlreadyExistsExceptoin if 'id' is an existing department id
	 */
	public void addDepartment(Integer id, DepartmentInfo info) throws IdAlreadyExistsExceptoin {
		/* Your Task */
		if (departments.containsKey(id)) throw new IdAlreadyExistsExceptoin("Department with id " + id + " already exists");
		departments.put(id, info);
	}

	/**
	 * Remove an existing department entry.
	 * @param id id of some employee
	 * @throws IdNotFoundException if 'id' is not an existing employee id
	 */
	public void removeDepartment(Integer id) throws IdNotFoundException {
		/* Your Task */
		if (!departments.containsKey(id)) throw new IdNotFoundException("Department with id " + id + " doest not exists");
		departments.remove(id);
	}

	/**
	 * Change the department of employee with id 'eid' to a new department with id 'did'.
	 * 
	 * You can assume that 'did' denotes a department different from
	 * the current department of the employee denoted by 'eid'.
	 * @param eid id of some employee
	 * @param did id of some department
	 * @throws IdNotFoundException if either eid is a non-existing employee id or did is a non-existing department id.
	 */
	public void changeDepartment(String eid, Integer did) throws IdNotFoundException {
		/* Your Task */
		if (!employees.containsKey(eid)) throw new IdNotFoundException("Employee with id " + eid + " does not exists");
		if (!departments.containsKey(did)) throw new IdNotFoundException("Department with id " + did + " doest not exists");
		employees.get(eid).setDepartmentId(did);
	}

	/**
	 * Retrieve the name of employee with id 'id'.
	 * @param id id of some employee
	 * @return name of the employee with id 'id'
	 * @throws IdNotFoundException if 'id' is not an existing employee id
	 */
	public String getEmployeeName(String id) throws IdNotFoundException {
		/* Your Task */
		if (!employees.containsKey(id)) throw new IdNotFoundException("Employee with id " + id + " does not exists");
		return employees.get(id).getName();
	}

	/**
	 * Retrieve the names of all employees of the department with id 'id'.
	 * If 'id' a non-existing department id, then return an empty list.
	 * @param id id of some department
	 * @return List of names of employees whose home department has id 'id'
	 */
	public ArrayList<String> getEmployeeNames(Integer id) {
		/* Your Task */
		if (!departments.containsKey(id)) return new ArrayList<>();
		ArrayList<String> list = new ArrayList<>();
		Collection<EmployeeInfo> set = employees.values();
		Iterator<EmployeeInfo> iterator = set.iterator();
		while (iterator.hasNext()) {
			EmployeeInfo info = iterator.next();
			if (info.getDepartmentId() == id)
				list.add(info.getName());
		}
		return list;
	}

	/**
	 * Retrieve an employee's department's information object.  
	 * @param id id of some existing employee
	 * @return The information object of the employee's home department
	 * @throws IdNotFoundException if 'id' is not an existing employee id
	 */
	public DepartmentInfo getDepartmentInfo(String id) throws IdNotFoundException {
		/* Your Task */
		if (!employees.containsKey(id)) throw new IdNotFoundException("Employee with id " + id + " doest not exists");
		return departments.get(employees.get(id).getDepartmentId());
	}

	/**
	 * Retrieve a list, sorted in increasing order, 
	 * the information objects of all stored employees.
	 * 
	 * Hints: 
	 * 1. Override the 'comareTo' method in EmployeeInfo class. 
	 * 2. Look up the Arrays.sort method in Java API. 
	 * @return A sorted list of information objects of all employees.
	 */
	public EmployeeInfo[] getSortedEmployeeInfo() {
		/* Your Task */
		EmployeeInfo[] info = new EmployeeInfo[employees.size()];
		Arrays.sort(employees.values().toArray(info));
		return info;
	}

	/**
	 * Retrieve the average salary of all employees in department with id 'id'.
	 * @param id id of some department 
	 * @return average salary of all employees in department with id 'id'
	 * @throws IdNotFoundException if id is not an existing department id
	 */
	public double getAverageSalary(Integer id) throws IdNotFoundException {
		/* Your Task */
		if (!departments.containsKey(id)) throw new IdNotFoundException("Department with id " + id + " doest not exists");
		int i = 0;
		double average = 0;
		Collection<EmployeeInfo> set = employees.values();
		Iterator<EmployeeInfo> iterator = set.iterator();
		while (iterator.hasNext()) {
			EmployeeInfo info = iterator.next();
			if (info.getDepartmentId() == id) {
				average += info.getSalary();
				i++;
			}
		}
		return average / i;
	}
	
	/**
	 * Retrieve the information object of the department with the highest average salary among its employees.
	 * @return the information object of the department with the highest average salary among its employees
	 * 
	 * Hint: Use 'getAverageSalary(Integer id)' as a helper method.
	 */
	public DepartmentInfo getDepartmentOfHighestAverageSalary() {	
		Set<Integer> s = departments.keySet();
		Iterator<Integer> iterator = s.iterator();
		int current = -1;
		double highest = 0;
		while (iterator.hasNext()) {
			try {
				int did = iterator.next();
				double ave = getAverageSalary(did);
				if (highest < ave || (highest == ave && current < did)) {
					current = did;
					highest = ave;
				}
			} catch (IdNotFoundException e) {
				continue;
			}
		}
		return departments.get(current);
	}
}
