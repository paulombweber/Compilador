package lexico;

public interface Constants extends ScannerConstants
{
    int EPSILON  = 0;
    int DOLLAR   = 1;

    int t_identificador = 2;
    int t_constanteInteger = 3;
    int t_constanteFloat = 4;
    int t_constanteString = 5;
    int t_TOKEN_6 = 6; //"+"
    int t_TOKEN_7 = 7; //"-"
    int t_TOKEN_8 = 8; //"*"
    int t_TOKEN_9 = 9; //"/"
    int t_TOKEN_10 = 10; //"="
    int t_TOKEN_11 = 11; //"&&"
    int t_TOKEN_12 = 12; //"||"
    int t_TOKEN_13 = 13; //"!"
    int t_TOKEN_14 = 14; //"=="
    int t_TOKEN_15 = 15; //"!="
    int t_TOKEN_16 = 16; //"<"
    int t_TOKEN_17 = 17; //"<="
    int t_TOKEN_18 = 18; //">"
    int t_TOKEN_19 = 19; //">="
    int t_TOKEN_20 = 20; //","
    int t_TOKEN_21 = 21; //";"
    int t_TOKEN_22 = 22; //"("
    int t_TOKEN_23 = 23; //")"
    int t_pr_boolean = 24;
    int t_pr_do = 25;
    int t_pr_else = 26;
    int t_pr_end = 27;
    int t_pr_false = 28;
    int t_pr_float = 29;
    int t_pr_if = 30;
    int t_pr_integer = 31;
    int t_pr_main = 32;
    int t_pr_print = 33;
    int t_pr_println = 34;
    int t_pr_scan = 35;
    int t_pr_string = 36;
    int t_pr_true = 37;
    int t_pr_void = 38;
    int t_pr_while = 39;

}
