//Test d'héritage
		class A{
			public int iA = test();
			
			public int test(){
				System.out.println("Bienvenue chez iA : " + iA);
				return 1;
			};		
			
			public A(){
				System.out.println("iA : "+iA);
				iA=3;
				System.out.println("iA : "+iA);
			}
		}
		
		class B extends A{
			public int iB = test2();		
			
			public B(){
				super();
				System.out.println("iB : "+iB);
				iB=2;
				System.out.println("iB : "+iB);
			}
			
			public int test2(){
				System.out.println("Bienvenue chez iB : " + iB);
				return 2;
			};
		}