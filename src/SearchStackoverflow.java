import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;

import org.apache.commons.lang.StringUtils;

import javax.swing.ImageIcon;

public class SearchStackoverflow extends AnAction {

  public SearchStackoverflow() {
    super(null, null, new ImageIcon(
        SearchStackoverflow.class.getClassLoader().getResource("icon/icon-so.ico")));
  }

  @Override
  public void actionPerformed(AnActionEvent e) {
    String stackOverflow = "https://stackoverflow.com/search?q=";
    CaretModel caretModel = e.getData(LangDataKeys.EDITOR).getCaretModel();
    Caret currentCaret =
        caretModel.getCurrentCaret();
    String selectedText = currentCaret.getSelectedText().trim();
    try {
      selectedText = selectedText.replaceAll(" ", "+");
    } catch (NullPointerException ex) {
      System.out.print("Text can't be empty" + ex.getMessage());
    }

    if (StringUtils.isNotBlank(selectedText)) {
      BrowserUtil.browse(stackOverflow + selectedText);
    } else {
      BrowserUtil.browse(stackOverflow);
    }
  }
}
