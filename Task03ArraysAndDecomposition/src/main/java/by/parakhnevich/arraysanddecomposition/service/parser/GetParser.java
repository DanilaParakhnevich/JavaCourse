package by.parakhnevich.arraysanddecomposition.service.parser;

/**
 * Class {@code Object} return parser proceeding of
 * its name
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.service.parser.Parse <--there are commands that use this class
 */
public class GetParser {
    public Parser getParser(String type){
        switch (type){
            case "int" :
                return new ParseToInteger();
            case "double" :
                return new ParseToDouble();
            case "byte" :
                return new ParseToByte();
            case "float" :
                return new ParseToFloat();
            case "short" :
                return new ParseToShort();
            case "long" :
                return new ParseToLong();
            default:
                return null;
        }
    }
}
