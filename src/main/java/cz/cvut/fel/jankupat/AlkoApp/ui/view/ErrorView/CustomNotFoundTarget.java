package cz.cvut.fel.jankupat.AlkoApp.ui.view.ErrorView;

import com.vaadin.flow.router.*;
import cz.cvut.fel.jankupat.AlkoApp.ui.MainLayout;

import javax.servlet.http.HttpServletResponse;

/**
 * The type Custom not found target.
 *
 * @author Patrik Jankuv
 * @created 4 /26/2021
 */
@ParentLayout(MainLayout.class)
public class CustomNotFoundTarget
        extends RouteNotFoundError {

    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<NotFoundException> parameter) {
        getElement().setText(
                " 404 page not found ");
        return HttpServletResponse.SC_NOT_FOUND;
    }
}