SELECT forename, surname,ExamMarks 
FROM StudentCourseAssignments, Student
WHERE course="7ZFG" AND Student.matricno=StudentCourseAssignments.StudentID
ORDER BY surname