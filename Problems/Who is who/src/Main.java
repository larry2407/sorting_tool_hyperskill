public static void determineWhoIsWho(Employee[]employees){
        for(Employee employee:employees){
        if(Developer.class.isInstance(employee)){
        System.out.println("DEV");
        }else if(DataAnalyst.class.isInstance(employee)){
        System.out.println("DA");
        }else if(Employee.class.isInstance(employee)){
        System.out.println("EMP");
        }
        }
        }