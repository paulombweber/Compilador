package sintatico;
public interface ParserConstants
{
	
	int			START_SYMBOL			= 43;
	
	int			FIRST_NON_TERMINAL		= 43;
	int			FIRST_SEMANTIC_ACTION	= 88;
	
	int[][]		PARSER_TABLE			=
										{
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1, -1, 1, -1, 1, 1, 1,
			-1, 1, 1, 1, 1, -1, 1, -1, 2, -1, -1},
			{-1, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 4, 3, -1, 3, -1, 4, 3, 4,
			-1, 3, 3, 3, 4, -1, 4, -1, -1, -1, -1},
			{-1, 6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 6, 5, 5, -1, -1, 6,
			-1, -1, 6, 6, 6, -1, -1, -1, 5, -1, -1, 5},
			{-1, 7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, -1, -1, -1,
			10, -1, -1, 9, 9, 8, -1, -1, -1, -1, -1, -1, -1},
			{-1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 14, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15, -1, -1, -1, -1, 15,
			-1, 15, -1, -1, -1, -1, 15, -1, 15, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, -1, -1, -1, -1, 16,
			-1, 16, -1, -1, -1, -1, 16, -1, 17, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18, -1, -1, -1, -1, 19,
			-1, 20, -1, -1, -1, -1, 21, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, 23, -1, -1, -1, -1, 23,
			-1, 23, -1, -1, -1, -1, 23, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 24, -1, -1, -1, -1, 24,
			-1, 24, -1, -1, -1, -1, 24, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, -1, -1, 25, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 27, -1, -1, -1, -1, 27,
			-1, 27, -1, -1, -1, -1, 27, -1, -1, -1, -1, -1, -1},
			{-1, 28, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, -1, 28, -1, -1,
			28, -1, -1, 28, 28, 28, -1, -1, -1, -1, -1, 29, 28},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 31},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 32, -1, -1, -1, -1, 32,
			-1, 32, -1, -1, -1, -1, 32, -1, -1, -1, -1, -1, -1},
			{-1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, 34, -1, -1, -1, -1, -1, -1, -1, -1, -1, 35, 34, -1, 34, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, 38, 39, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 42, -1,
			-1, -1, -1, -1, -1, -1, -1, 41, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, 43, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, 44, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, 45, 46, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, 47, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, 48, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, 49, 49, 49, 49, 49, 49, -1, -1, -1, -1, -1, 49, -1, -1, -1, -1, -1, -1, -1, -1, 49, -1, -1, -1, -1, -1, 49, -1,
			-1, -1, -1, -1, -1, -1, -1, 49, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 51, -1, -1, 50, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			52, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 54, 53, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 55, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 56, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, 58, 58, 58, 58, 58, 58, -1, -1, -1, -1, -1, 58, -1, -1, -1, -1, -1, -1, -1, -1, 58, 57, -1, -1, -1, -1, 58, -1,
			-1, -1, -1, -1, -1, -1, -1, 58, -1, -1, -1, -1, -1},
			{-1, 59, 59, 59, 59, 59, 59, -1, -1, -1, -1, -1, 59, -1, -1, -1, -1, -1, -1, -1, -1, 59, -1, -1, -1, -1, -1, 59, -1,
			-1, -1, -1, -1, -1, -1, -1, 59, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, 61, -1, -1, -1, -1, -1, -1, -1, 60, 60, -1, 60, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, 63, 63, 63, 63, 63, 63, -1, -1, -1, -1, -1, 66, -1, -1, -1, -1, -1, -1, -1, -1, 63, -1, -1, -1, -1, -1, 65, -1,
			-1, -1, -1, -1, -1, -1, -1, 64, -1, -1, -1, -1, -1},
			{-1, 67, 67, 67, 67, 67, 67, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 67, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 68, 68, -1, 69, 69, 69, 69, 69, 69, 68, 68, -1, 68, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 70, 71, 72, 73, 74, 75, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, 76, 76, 76, 76, 76, 76, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 76, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, 79, 78, -1, -1, -1, 77, 77, -1, 77, 77, 77, 77, 77, 77, 77, 77, -1, 77, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, 80, 80, 80, 80, 80, 80, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 80, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, 81, 81, 82, 83, -1, 81, 81, -1, 81, 81, 81, 81, 81, 81, 81, 81, -1, 81, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, 84, 85, 86, 87, 89, 90, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 88, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, 91, 91, 91, 91, -1, 91, 91, -1, 91, 91, 91, 91, 91, 91, 91, 91, 92, 91, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
										};
	
	int[][]		PRODUCTIONS				=
										{
										{32, 44, 45, 46, 27},
										{0},
										{40, 59, 44},
										{0},
										{50, 45},
										{0},
										{47, 46},
										{48},
										{65},
										{66},
										{71},
										{73},
										{2, 49},
										{64},
										{74},
										{51, 2, 22, 53, 23, 57, 46, 58, 27},
										{52},
										{38},
										{24},
										{29},
										{31},
										{36},
										{0},
										{54},
										{56, 55},
										{0},
										{20, 54},
										{52, 2},
										{0},
										{41, 59, 57},
										{0},
										{42, 76, 21},
										{52, 60, 62, 21},
										{2, 61},
										{0},
										{20, 60},
										{0},
										{10, 63},
										{3},
										{4},
										{5},
										{37},
										{28},
										{10, 76, 21},
										{35, 22, 60, 23, 21},
										{67},
										{68},
										{33, 22, 69, 23, 21},
										{34, 22, 69, 23, 21},
										{76, 70},
										{0},
										{20, 69},
										{30, 22, 76, 23, 46, 72, 27, 21},
										{0},
										{26, 46},
										{25, 46, 39, 22, 76, 23, 21},
										{22, 75, 23, 21},
										{0},
										{69},
										{78, 77},
										{0},
										{12, 78, 77},
										{11, 78, 77},
										{79},
										{37},
										{28},
										{13, 78},
										{82, 80},
										{0},
										{81, 82},
										{14},
										{15},
										{16},
										{17},
										{18},
										{19},
										{84, 83},
										{0},
										{7, 84, 83},
										{6, 84, 83},
										{86, 85},
										{0},
										{8, 86, 85},
										{9, 86, 85},
										{2, 87},
										{3},
										{4},
										{5},
										{22, 76, 23},
										{6, 86},
										{7, 86},
										{0},
										{22, 69, 23}
										};
	
	String[]	PARSER_ERROR			=
										{
										"",
										"Era esperado fim de programa",
										"Era esperado identificador",
										"Era esperado constanteInteger",
										"Era esperado constanteFloat",
										"Era esperado constanteString",
										"Era esperado \"+\"",
										"Era esperado \"-\"",
										"Era esperado \"*\"",
										"Era esperado \"/\"",
										"Era esperado \"=\"",
										"Era esperado \"&&\"",
										"Era esperado \"||\"",
										"Era esperado \"!\"",
										"Era esperado \"==\"",
										"Era esperado \"!=\"",
										"Era esperado \"<\"",
										"Era esperado \"<=\"",
										"Era esperado \">\"",
										"Era esperado \">=\"",
										"Era esperado \",\"",
										"Era esperado \";\"",
										"Era esperado \"(\"",
										"Era esperado \")\"",
										"Era esperado pr_boolean",
										"Era esperado pr_do",
										"Era esperado pr_else",
										"Era esperado pr_end",
										"Era esperado pr_false",
										"Era esperado pr_float",
										"Era esperado pr_if",
										"Era esperado pr_integer",
										"Era esperado pr_main",
										"Era esperado pr_print",
										"Era esperado pr_println",
										"Era esperado pr_scan",
										"Era esperado pr_string",
										"Era esperado pr_true",
										"Era esperado pr_void",
										"Era esperado pr_while",
										"Era esperado pr_global",
										"Era esperado pr_local",
										"Era esperado pr_return",
										"Era esperado main, encontrado: ", // DONE
			"<variaveis_globais> inv�lido",
			"<lista_modulos> inv�lido",
			"<lista_comandos> inv�lido",
			"<comandos> inv�lido",
			"<atribuicao_chamada_modulo> inv�lido",
			"<atribuicao_chamada_modulo1> inv�lido",
			"<modulo> inv�lido",
			"<tipo_modulo> inv�lido",
			"<tipo> inv�lido",
			"<lista_parametros> inv�lido",
			"<parametros> inv�lido",
			"<parametros1> inv�lido",
			"<parametro> inv�lido",
			"<variaveis_locais> inv�lido",
			"<retorno> inv�lido",
			"<variaveis> inv�lido",
			"<lista_identificadores> inv�lido",
			"<lista_identificadores1> inv�lido",
			"<valor> inv�lido",
			"<constante> inv�lido",
			"<atribuicao> inv�lido",
			"<entrada> inv�lido",
			"<saida> inv�lido",
			"<print> inv�lido",
			"<println> inv�lido",
			"<lista_expressoes> inv�lido",
			"<lista_expressoes1> inv�lido",
			"<selecao> inv�lido",
			"<else> inv�lido",
			"<repeticao> inv�lido",
			"<chamada_modulo> inv�lido",
			"<parametros_reais> inv�lido",
			"<expressao> inv�lido",
			"<expressao1> inv�lido",
			"<elemento> inv�lido",
			"<relacional> inv�lido",
			"<relacional1> inv�lido",
			"<operador_relacional> inv�lido",
			"<aritmetica> inv�lido",
			"<aritmetica1> inv�lido",
			"<termo> inv�lido",
			"<termo1> inv�lido",
			"<fator> inv�lido",
			"<fator1> inv�lido"
										};
}
