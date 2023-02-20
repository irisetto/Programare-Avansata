class HelloWorld 
{ 
    public static int sumCif(int n)
    {
        int sum=0;
        int u;
        while(n>0)
        {
            u=n%10;
            sum=sum+u;
            n=n/10;
        }
        return sum;
    }
    public static void main(String[] args)
     { 
        String languages[]={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n=n*3;
        int nr=Integer.parseInt("10101",2);
        n=n+nr;
        int nr2=Integer.parseInt("FF",16);
        n=n+nr2;
        n=n*6; 
        System. out. println(n); 
        int sum2=sumCif(n);
        System. out. println(sum2);
        while(sum2>9) sum2=sumCif(sum2); 
        System. out. println(sum2);
        System. out. println("Hello World!");  
        System.out.println("Willy-nilly, this semester I will learn "  + languages[sum2]);
    } 
}
