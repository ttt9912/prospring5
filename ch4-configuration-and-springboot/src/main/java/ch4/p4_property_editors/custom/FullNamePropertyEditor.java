package ch4.p4_property_editors.custom;

import java.beans.PropertyEditorSupport;

/*
 * Ermöglicht, dass die Properties firstName und lastName als ein
 * String im ctx.xml definiert werden können.
 */
public class FullNamePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        String[] name = text.split("\\s");

        setValue(new FullName(name[0], name[1]));
    }
}
