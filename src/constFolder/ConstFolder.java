package constFolder;

import CPP.Absyn.*;

public class ConstFolder {
	public static String eval(Exp e){
		System.out.println("Eval Expression:");
		String s =  e.accept(new FoldExp(), null);
		
		return s ;
	}
	
	public static void eval(Stm s){
		System.out.println("Eval Statement");
		s.accept(new FoldStm(), null);
		
		return ;
	}
	
	public static String eval(Arg a){
		System.out.println("Eval Argument");
		String s =  a.accept(new FoldArg(), null);
		return s;
	}
	
	public static void eval(Def d){
		System.out.println("Eval Definition");
		d.accept(new FoldDef(), null);
		return ;
	}
	
	public static void eval(Program p){
		System.out.println("Eval Programm");
		
		 p.accept(new FoldProgram(),null);
		return;
	}
	
	
	
}
