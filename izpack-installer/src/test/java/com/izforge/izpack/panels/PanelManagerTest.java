package com.izforge.izpack.panels;

import com.izforge.izpack.bootstrap.IPanelComponent;
import com.izforge.izpack.data.Panel;
import com.izforge.izpack.installer.data.InstallData;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test for panel manager
 */
public class PanelManagerTest {
    @Mock
    private InstallData installData;
    @Mock
    private IPanelComponent panelComponent;
    private PanelManager panelManager;

    @Before
    public void initMock() {
        MockitoAnnotations.initMocks(getClass());
        panelManager = new PanelManager(installData, panelComponent);
    }

    @Test
    public void resolveClassNameShouldAddDefaultPrefix() throws Exception {
        Class<?> aClass = panelManager.resolveClassName("HelloPanel");
        assertThat(aClass.getName(), Is.is("com.izforge.izpack.panels.HelloPanel"));
        aClass = panelManager.resolveClassName("FinishPanel");
        assertThat(aClass.getName(), Is.is("com.izforge.izpack.panels.FinishPanel"));
    }

    @Test
    public void resolveClassNameShouldNotAddPrefixWithCompleteClass() throws Exception {
        Class<?> aClass = panelManager.resolveClassName("com.izforge.izpack.panels.HelloPanel");
        assertThat(aClass.getName(), Is.is("com.izforge.izpack.panels.HelloPanel"));
        aClass = panelManager.resolveClassName("com.izforge.izpack.bootstrap.PanelComponent");
        assertThat(aClass.getName(), Is.is("com.izforge.izpack.bootstrap.PanelComponent"));
    }

    @Test(expected = ClassNotFoundException.class)
    public void resolveClassNameShouldThrowException() throws Exception {
        panelManager.resolveClassName("unknown");
    }



}
