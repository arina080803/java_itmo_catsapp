rootProject.name = "java_itmo_catsapp"
include("Lab2")
include("Lab2:DAO")
findProject(":Lab2:DAO")?.name = "DAO"
include("Lab2:Service")
findProject(":Lab2:Service")?.name = "Service"
include("Lab2:Controller")
findProject(":Lab2:Controller")?.name = "Controller"
include("Lab5")
include("Lab5:CatService")
findProject(":lLab5:CatService")?.name = "CatService"
include("Lab5:OwnerService")
findProject(":Lab5:OwnerService")?.name = "OwnerService"
include("Lab5:UserService")
findProject(":Lab5:UserService")?.name = "UserService"
include("Lab5:CatsMicroservice")
findProject(":Lab5:CatsMicroservice")?.name = "CatsMicroservice"
include("Lab5:CatsMicroservice:CatController")
findProject(":Lab5:CatsMicroservice:CatController")?.name = "CatController"
include("Lab5:CatsMicroservice:CatClient")
findProject(":Lab5:CatsMicroservice:CatClient")?.name = "CatClient"
include("Lab5:OwnerMicroservice")
findProject(":Lab5:OwnerMicroservice")?.name = "OwnerMicroservice"
include("Lab5:OwnerMicroservice:OwnerController")
findProject(":Lab5:OwnerMicroservice:OwnerController")?.name = "OwnerController"
include("Lab5:OwnerMicroservice:OwnerClient")
findProject(":Lab5:OwnerMicroservice:OwnerClient")?.name = "OwnerClient"
include("Lab5:UserMicroservice")
findProject(":Lab5:UserMicroservice")?.name = "UserMicroservice"