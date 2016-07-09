package constFolder;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import CPP.PrettyPrinter;
import CPP.Yylex;
import CPP.parser;
import CPP.sym;
import CPP.Absyn.Program;
import java_cup.runtime.SymbolFactory;


public class Main {

	static String filename;
	static CPP.Absyn.Program optimized_tree;

	public static void main(String[] args) {
		//		if(args.length==0){
		//			System.out.println("No Input argument!");
		//			return;
		//		}
		try {


			//File f = new File("files/good11.cc");
			File f = new File("files/FoldTest.cc");
			//File f = new File(args[0]);

			f.createNewFile();
			filename = f.getName();
			if(!f.exists()){
				System.out.println("File does not exist");
			}
			Yylex l = new Yylex(new FileReader(f));


			@SuppressWarnings("deprecation")
			parser p = new parser(l);

			CPP.Absyn.Program parse_tree;
			
			try {
				
				/**
				 * hier vor muss der c++ Code veraendert werden
				 * also fold constants, 
				 * danach die .cc Datei wieder parsen
				 */
				parse_tree = p.pProgram();
				
				
				/**
				 * Optimierung: Konstantenfaltung
				 */
				optimize(parse_tree);
				
				

				System.out.println();
				System.out.println("Parse Succesful!");
				System.out.println();
				System.out.println("[Abstract Syntax]");
				System.out.println();
				System.out.println(PrettyPrinter.show(parse_tree));
				System.out.println();
				System.out.println("[Linearized Tree]");
				System.out.println();
				System.out.println(PrettyPrinter.print(parse_tree));
				
				/**
				 * Hier muessten erst die Konstanten gefaltet werden, 
				 * bevor der Code generiert wird
				 */
				

				generate(parse_tree);


			} catch (Exception e) 
			{
				System.out.println(e);
			}

		}catch(IOException e){
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 
	 * implemnt the functions
	 * 			-read through the doc of llvm to find the correct string values for the functions
	 * print the string in an existing file
	 * make java to execute jasmin and with that text file
	 * For any 
	 * 
	 * 
	 * maybe it does make sense to do sysos in every visit and eval 
	 * @param parse_tree
	 */

	private static void generate(Program parse_tree) {

		Module.variableStack = new LinkedList<>();
		Module.Output = new LinkedList<>();
		System.out.println(parse_tree.toString());

		ConstFolder.eval(parse_tree);

		//parse_tree.accept(new CompileProgram(),null);

		Module.finish();

		Filewriter.write(filename);

	}
	
	private static void optimize(Program parse_tree)
	{
		/**
		 * Kopie des AST wird veraendert
		 */
		optimized_tree = parse_tree;
		
		
	}
	
	public void compareTrees()
	{
		
	}



}
