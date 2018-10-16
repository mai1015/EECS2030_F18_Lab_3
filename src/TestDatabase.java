import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/* 
 * READ THE PDF INSTRUCTION BEFORE GETTING STARTED! 
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDatabase { 
	
	Database db;
	
	/*
	 * This method is executed before each test method is executed.
	 */
	@Before
    public void setUp() {
		db = new Database();
    }
	
	private void addEmployees() {
		try {
			db.addEmployee("e1", new EmployeeInfo("Heeyeon"	, 100, 1));
			db.addEmployee("e2", new EmployeeInfo("Jiyoon"	, 500, 2)); 
			db.addEmployee("e3", new EmployeeInfo("Jaebin"	, 700, 3));
			db.addEmployee("e4", new EmployeeInfo("Suyeon"	, 700, 2));
			db.addEmployee("e5", new EmployeeInfo("Yuna"	, 500, 1));
			db.addEmployee("e6", new EmployeeInfo("Sunhye"	, 600, 3));
			db.addEmployee("e7", new EmployeeInfo("Jihye"	, 700, 1));
		} catch (IdAlreadyExistsExceptoin e) {
			// exception not expected
			fail();
		}
	}
	
	private void addDepartments() {
		try {
			db.addDepartment(1, new DepartmentInfo("ACCOUNTING"	, "Toronto"));
			db.addDepartment(2, new DepartmentInfo("IT"			, "Seoul"));
			db.addDepartment(3, new DepartmentInfo("RESEARCH"	, "London"));
		} catch (IdAlreadyExistsExceptoin e) {
			// exception not expected
			fail();
		} 
	}
	
	@Test
	public void test_Constructor() { 
		assertEquals(db.employees.size(), 0);
		assertEquals(db.departments.size(), 0);
	}
	
	@Test
	public void test_addEmployee_abnormal() {
		addEmployees();
		
		try {
			db.addEmployee("e3", new EmployeeInfo("Jiwon", 400, 2));
			fail();
		} catch (IdAlreadyExistsExceptoin e) {
			// exception expected
		}
	}

	@Test
	public void test_addEmployee_normal() {
		try {
			db.addEmployee("e1", new EmployeeInfo("Heeyeon"	, 100, 1));
			db.addEmployee("e2", new EmployeeInfo("Jiyoon"	, 500, 2));
			db.addEmployee("e3", new EmployeeInfo("Jaebin"	, 700, 3));
			db.addEmployee("e4", new EmployeeInfo("Suyeon"	, 700, 2));
			db.addEmployee("e5", new EmployeeInfo("Yuna"	, 500, 1));
			db.addEmployee("e6", new EmployeeInfo("Sunhye"	, 600, 3));
			db.addEmployee("e7", new EmployeeInfo("Jihye"	, 700, 1));
		} catch (IdAlreadyExistsExceptoin e) {
			// exception not expected
			fail();
		} 
		assertEquals(7, db.employees.size());
		assertEquals(new EmployeeInfo("Heeyeon"	, 100, 1), db.employees.get("e1"));
		assertEquals(new EmployeeInfo("Jiyoon"	, 500, 2), db.employees.get("e2"));
		assertEquals(new EmployeeInfo("Jaebin"	, 700, 3), db.employees.get("e3"));
		assertEquals(new EmployeeInfo("Suyeon"	, 700, 2), db.employees.get("e4"));
		assertEquals(new EmployeeInfo("Yuna"	, 500, 1), db.employees.get("e5"));
		assertEquals(new EmployeeInfo("Sunhye"	, 600, 3), db.employees.get("e6"));
		assertEquals(new EmployeeInfo("Jihye"	, 700, 1), db.employees.get("e7"));
	}
	
	@Test
	public void test_removeEmployee_abnormal() {
		addEmployees();
		
		try {
			db.removeEmployee("e8");
			fail();
		} catch (IdNotFoundException e) {
			// exception expected 
		}
	}
	
	@Test
	public void test_removeEmployee_normal() {
		addEmployees();
		
		try {
			db.removeEmployee("e3");
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		}
		
		assertEquals(6, db.employees.size());
		assertEquals(new EmployeeInfo("Heeyeon"	, 100, 1), db.employees.get("e1"));
		assertEquals(new EmployeeInfo("Jiyoon"	, 500, 2), db.employees.get("e2"));
		assertTrue(db.employees.get("e3") == null);
		assertEquals(new EmployeeInfo("Suyeon"	, 700, 2), db.employees.get("e4"));
		assertEquals(new EmployeeInfo("Yuna"	, 500, 1), db.employees.get("e5"));
		assertEquals(new EmployeeInfo("Sunhye"	, 600, 3), db.employees.get("e6"));
		assertEquals(new EmployeeInfo("Jihye"	, 700, 1), db.employees.get("e7"));
		
		try {
			db.removeEmployee("e6");
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		}
		
		assertEquals(5, db.employees.size());
		assertEquals(new EmployeeInfo("Heeyeon"	, 100, 1), db.employees.get("e1"));
		assertEquals(new EmployeeInfo("Jiyoon"	, 500, 2), db.employees.get("e2"));
		assertTrue(db.employees.get("e3") == null);
		assertEquals(new EmployeeInfo("Suyeon"	, 700, 2), db.employees.get("e4"));
		assertEquals(new EmployeeInfo("Yuna"	, 500, 1), db.employees.get("e5"));
		assertTrue(db.employees.get("e6") == null);
		assertEquals(new EmployeeInfo("Jihye"	, 700, 1), db.employees.get("e7"));
	}
	
	@Test
	public void test_addDepartment_abnormal() {
		addDepartments();
		
		try {
			db.addDepartment(2, new DepartmentInfo("PUBLIC RELATION", "Vancouver"));
			fail();
		} catch (IdAlreadyExistsExceptoin e) {
			// exception expected
		} 
	}
	
	@Test
	public void test_addDepartment_normal() {
		try {
			db.addDepartment(1, new DepartmentInfo("ACCOUNTING"	, "Toronto"));
			db.addDepartment(2, new DepartmentInfo("IT"			, "Seoul"));
			db.addDepartment(3, new DepartmentInfo("RESEARCH"	, "London"));
		} catch (IdAlreadyExistsExceptoin e) {
			// exception not expected
			fail();
		} 
		assertEquals(3, db.departments.size());
		assertEquals(new DepartmentInfo("ACCOUNTING", "Toronto"), db.departments.get(1));
		assertEquals(new DepartmentInfo("IT"		, "Seoul")	, db.departments.get(2));
		assertEquals(new DepartmentInfo("RESEARCH"	, "London")	, db.departments.get(3));
	}
	
	@Test
	public void test_removeDepartment_abnormal() {
		addDepartments();
		
		try {
			db.removeDepartment(4);
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		} 
	}
	
	@Test
	public void test_removeDepartment_normal() {
		addDepartments();
		
		try {
			db.removeDepartment(2);
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		} 
		assertEquals(2, db.departments.size());
		assertEquals(new DepartmentInfo("ACCOUNTING", "Toronto"), db.departments.get(1));
		assertTrue(db.departments.get(2) == null);
		assertEquals(new DepartmentInfo("RESEARCH"	, "London")	, db.departments.get(3));
		
		try {
			db.removeDepartment(3);
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		} 
		assertEquals(1, db.departments.size());
		assertEquals(new DepartmentInfo("ACCOUNTING", "Toronto"), db.departments.get(1));
		assertTrue(db.departments.get(2) == null);
		assertTrue(db.departments.get(3) == null);
	}
	
	@Test
	public void test_changeDepartment_abnormal() {
		addDepartments();
		addEmployees();
		
		try {
			// invalid employee id
			db.changeDepartment("e8", 3);
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		}
		
		try {
			// invalid department id
			db.changeDepartment("e2", 4);
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		}
	}
	
	@Test
	public void test_changeDepartment_normal() {
		addDepartments();
		addEmployees();
		
		try {
			db.changeDepartment("e2", 3);
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		}
		
		assertEquals(7, db.employees.size());
		/* changed employee */
		assertEquals(new EmployeeInfo("Jiyoon"	, 500, 3), db.employees.get("e2"));
		/* other employees remain unchanged */
		assertEquals(new EmployeeInfo("Heeyeon"	, 100, 1), db.employees.get("e1"));
		assertEquals(new EmployeeInfo("Jaebin"	, 700, 3), db.employees.get("e3"));
		assertEquals(new EmployeeInfo("Suyeon"	, 700, 2), db.employees.get("e4"));
		assertEquals(new EmployeeInfo("Yuna"	, 500, 1), db.employees.get("e5"));
		assertEquals(new EmployeeInfo("Sunhye"	, 600, 3), db.employees.get("e6"));
		assertEquals(new EmployeeInfo("Jihye"	, 700, 1), db.employees.get("e7"));
		
		try {
			db.changeDepartment("e7", 2);
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		}
		
		assertEquals(7, db.employees.size());
		/* changed employee */
		assertEquals(new EmployeeInfo("Jihye"	, 700, 2), db.employees.get("e7"));
		/* other employees remain unchanged */
		assertEquals(new EmployeeInfo("Heeyeon"	, 100, 1), db.employees.get("e1"));
		assertEquals(new EmployeeInfo("Jiyoon"	, 500, 3), db.employees.get("e2"));
		assertEquals(new EmployeeInfo("Jaebin"	, 700, 3), db.employees.get("e3"));
		assertEquals(new EmployeeInfo("Suyeon"	, 700, 2), db.employees.get("e4"));
		assertEquals(new EmployeeInfo("Yuna"	, 500, 1), db.employees.get("e5"));
		assertEquals(new EmployeeInfo("Sunhye"	, 600, 3), db.employees.get("e6"));
	}
	
	@Test
	public void test_getEmployeeName_abnormal() {
		addDepartments();
		addEmployees();
		
		try {
			String name = db.getEmployeeName("e8");
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		} 
	}
	
	@Test
	public void test_getEmployeeName_normal() {
		addDepartments();
		addEmployees();
		
		try {
			assertEquals("Heeyeon"	, db.getEmployeeName("e1"));
			assertEquals("Jiyoon"	, db.getEmployeeName("e2"));
			assertEquals("Jaebin"	, db.getEmployeeName("e3"));
			assertEquals("Suyeon"	, db.getEmployeeName("e4"));
			assertEquals("Yuna"		, db.getEmployeeName("e5"));
			assertEquals("Sunhye"	, db.getEmployeeName("e6"));
			assertEquals("Jihye"	, db.getEmployeeName("e7"));
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		} 
	}
	
	@Test
	public void test_getEmployeeNames() {
		addDepartments();
		addEmployees();
		
		ArrayList<String> names;
		names = db.getEmployeeNames(1);
		assertEquals(3, names.size());
		assertTrue(names.contains("Heeyeon"));
		assertTrue(names.contains("Yuna"));
		assertTrue(names.contains("Jihye"));
		names = db.getEmployeeNames(2);
		assertEquals(2, names.size());
		assertTrue(names.contains("Jiyoon"));
		assertTrue(names.contains("Suyeon"));
		names = db.getEmployeeNames(3);
		assertEquals(2, names.size());
		assertTrue(names.contains("Jaebin"));
		assertTrue(names.contains("Sunhye"));
		// non-existing department id
		names = db.getEmployeeNames(4);
		assertEquals(0, names.size());
	}
	
	@Test
	public void test_getDepartmentInfo_abnormal() {
		addDepartments();
		addEmployees();
		
		try {
			DepartmentInfo name = db.getDepartmentInfo("e8");
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		} 
	}
	
	@Test
	public void test_getDepartmentInfo_normal() {
		addDepartments();
		addEmployees();
		
		try {
			assertEquals(new DepartmentInfo("ACCOUNTING", "Toronto"), db.getDepartmentInfo("e1"));
			assertEquals(new DepartmentInfo("IT"		, "Seoul")	, db.getDepartmentInfo("e2"));
			assertEquals(new DepartmentInfo("RESEARCH"	, "London")	, db.getDepartmentInfo("e3"));
			assertEquals(new DepartmentInfo("IT"		, "Seoul")	, db.getDepartmentInfo("e4"));
			assertEquals(new DepartmentInfo("ACCOUNTING", "Toronto"), db.getDepartmentInfo("e5"));
			assertEquals(new DepartmentInfo("RESEARCH"	, "London")	, db.getDepartmentInfo("e6"));
			assertEquals(new DepartmentInfo("ACCOUNTING", "Toronto"), db.getDepartmentInfo("e7"));
			
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		} 
	}
	
	@Test
	public void test_getSortedEmployeeInfo_normal() {
		addDepartments();
		addEmployees();
		
		EmployeeInfo[] sorted = db.getSortedEmployeeInfo();
		EmployeeInfo[] expected = {
				new EmployeeInfo("Jaebin"	, 700, 3),
				new EmployeeInfo("Suyeon"	, 700, 2),
				new EmployeeInfo("Jihye"	, 700, 1),
				new EmployeeInfo("Sunhye"	, 600, 3),
				new EmployeeInfo("Jiyoon"	, 500, 2),
				new EmployeeInfo("Yuna"		, 500, 1),
				new EmployeeInfo("Heeyeon"	, 100, 1)
		};
		assertEquals(7, sorted.length);
		assertArrayEquals(expected, sorted);
	}
	
	@Test
	public void test_getAverageSalary_abnormal() {
		addDepartments();
		addEmployees();
		
		try {
			double avgSalary = db.getAverageSalary(4);
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		} 
	}
	
	@Test
	public void test_getAverageSalary_normal() {
		addDepartments();
		addEmployees();
		
		try {
			// precision of getAverageSalary is within a tolerance of +/- 0.1
			assertEquals((100 + 500 + 700) / 3.0, db.getAverageSalary(1), 0.1);
			assertEquals((500 + 700) / 2.0		, db.getAverageSalary(2), 0.1);
			assertEquals((700 + 600) / 2.0		, db.getAverageSalary(3), 0.1);
			
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		} 
	}
	
	@Test
	public void test_getDepartmentOfHighestAverageSalary_normal() {
		addDepartments();
		addEmployees();
		
		assertEquals(
			new DepartmentInfo("RESEARCH", "London"), 
			db.getDepartmentOfHighestAverageSalary());
	}
}
