package project.programming.elliajah.studentknows1;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public Map<String,String> getNameFromMail(String mail) {
        String almostName = mail.substring(0, mail.indexOf('@')).replace('.', ' ');
        String name = almostName.substring(0, almostName.indexOf(" "));
        String bigname= almostName.substring(name.length()).trim();
        Map<String,String> names = new HashMap<>();
        names.put("first",name);
        names.put("second",bigname);
        return names;
    }

    public void setOraronViews(String zi, Orar orar, TextView ora8, TextView ora10, TextView ora12, TextView ora14, TextView ora16, TextView ora18, TextView ora20) {
        List<OrarOra> orelePeZi = orar.getMDayAndClass().get(zi);
        int iter = 0;
        for (OrarOra x : orelePeZi) {
            String or = x.getMOra();
            String cls = x.getMClass();

            if (!cls.isEmpty()) {
                String[] totCeTrebe = cls.split(",");
                switch (iter) {
                    case 0:

                        ora8.setText(Html.fromHtml("<big>"+"<font color:'#3c20aa'>"+"<b>"+or +"</b>"+"</font>"+"</big>"+ "<br />" +
                                totCeTrebe[0] + "," + totCeTrebe[1] +"<br />" +
                                totCeTrebe[2] +"<br />" +
                                totCeTrebe[3],Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 1:
                        ora10.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />" +
                                totCeTrebe[0] + "," + totCeTrebe[1] +"<br />" +
                                totCeTrebe[2] +"<br />" +
                                totCeTrebe[3],Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 2:
                        ora12.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />" +
                                totCeTrebe[0] + "," + totCeTrebe[1] +"<br />" +
                                totCeTrebe[2] +"<br />" +
                                totCeTrebe[3],Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 3:
                        ora14.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />" +
                                totCeTrebe[0] + "," + totCeTrebe[1] +"<br />" +
                                totCeTrebe[2] +"<br />" +
                                totCeTrebe[3],Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 4:
                        ora16.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />" +
                                totCeTrebe[0] + "," + totCeTrebe[1] +"<br />" +
                                totCeTrebe[2] +"<br />" +
                                totCeTrebe[3],Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 5:
                        ora18.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />" +
                                totCeTrebe[0] + "," + totCeTrebe[1] +"<br />" +
                                totCeTrebe[2] +"<br />" +
                                totCeTrebe[3],Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 6:
                        ora20.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />" +
                                totCeTrebe[0] + "," + totCeTrebe[1] +"<br />" +
                                 totCeTrebe[2] +"<br />" +
                                totCeTrebe[3],Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;

                }
            } else {
                switch (iter) {
                    case 0:
                       // ora8.setVisibility(View.GONE);
                       ora8.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />",Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 1:
                        //ora10.setVisibility(View.GONE);
                        ora10.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />",Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 2:
                        //ora12.setVisibility(View.GONE);
                         ora12.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />",Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 3:
                      //  ora14.setVisibility(View.GONE);
                      ora14.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />",Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 4:
                        //ora16.setVisibility(View.GONE);
                        ora16.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />",Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 5:
                       // ora18.setVisibility(View.GONE);
                        ora18.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />",Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                    case 6:
                       // ora20.setVisibility(View.GONE);
                       ora20.setText(Html.fromHtml("<big>"+"<b>"+or +"</b>"+"</big>"+ "<br />",Html.FROM_HTML_MODE_COMPACT));
                        iter++;
                        break;
                }
            }
        }
    }
    public boolean fileExists(Context context, String filename) {
        File file = context.getFileStreamPath(filename);
        if(file == null || !file.exists()) {
            return false;
        }
        return true;
    }
}
