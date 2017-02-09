SELECT FirstName, Surname
FROM  Staff, LecturerCourseAssignments
WHERE Staff.Status="Lecturer" AND LecturerCourseAssignments.Course ="7ZFG" AND LecturerCourseAssignments.StaffID=Staff.StaffID