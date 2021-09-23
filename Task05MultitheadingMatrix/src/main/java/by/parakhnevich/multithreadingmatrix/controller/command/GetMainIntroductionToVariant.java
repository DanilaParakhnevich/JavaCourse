package by.parakhnevich.multithreadingmatrix.controller.command;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.view.locale.LocaleSingleton;

public class GetMainIntroductionToVariant {
    public String get(Matrix matrix)
    {
        return '\n' + LocaleSingleton.getInstance().getResourceBundle().
                getString("BEFORE") +
                '\n' + matrix.toPureString() + '\n' +
                LocaleSingleton.getInstance().getResourceBundle().
                        getString("AFTER") + '\n';
    }
}
