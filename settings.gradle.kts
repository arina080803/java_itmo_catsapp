rootProject.name = "java_itmo_catsapp"
include("Lab2")
include("Lab2:DAO")
findProject(":Lab2:DAO")?.name = "DAO"
include("Lab2:Service")
findProject(":Lab2:Service")?.name = "Service"
include("Lab2:Controller")
findProject(":Lab2:Controller")?.name = "Controller"

