/*
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import org.japo.java.events.AEM;
import org.japo.java.libraries.UtilesSwing;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class GUI extends JFrame {

    // Propiedades App
    public static final String PRP_FAVICON_RESOURCE = "favicon_resource";
    public static final String PRP_FONT_BANNER_RESOURCE = "font_banner_resource";
    public static final String PRP_FONT_INTERFACE_RESOURCE = "font_interface_resource";
    public static final String PRP_FORM_HEIGHT = "form_height";
    public static final String PRP_FORM_TITLE = "form_title";
    public static final String PRP_FORM_WIDTH = "form_width";
    public static final String PRP_LOOK_AND_FEEL_PROFILE = "look_and_feel_profile";

    // Valores por Defecto
    public static final String DEF_FAVICON_RESOURCE = "img/favicon.png";
    public static final String DEF_FONT_BANNER_SYSTEM_NAME = UtilesSwing.FONT_LUCIDA_BRIGHT_NAME;
    public static final String DEF_FONT_BANNER_FALLBACK_NAME = "Serif";
    public static final String DEF_FONT_INTERFACE_SYSTEM_NAME = UtilesSwing.FONT_LUCIDA_SANS_NAME;
    public static final String DEF_FONT_INTERFACE_FALLBACK_NAME = "Dialog";
    public static final int DEF_FORM_HEIGHT = 300;
    public static final String DEF_FORM_TITLE = "Swing Manual Title";
    public static final int DEF_FORM_WIDTH = 500;
    public static final String DEF_LOOK_AND_FEEL_PROFILE = UtilesSwing.LNF_WINDOWS_PROFILE;

    // Referencias
    private Properties prp;

    // Componentes
    private JLabel lblRotulo;
    private JComboBox<String> cbbFamilia;
    private JPanel pnlControl;
    private JPanel pnlPpal;

    // Fuentes
    private Font fntRotulo;
    private Font fntInterfaz;

    // Constructor
    public GUI(Properties prp) {
        // Conectar Referencia
        this.prp = prp;

        // Inicialización Anterior
        initBefore();

        // Creación Interfaz
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción del IGU
    private void initComponents() {
        // Fuentes
        fntRotulo = UtilesSwing.generarFuenteRecurso(
                prp.getProperty(PRP_FONT_BANNER_RESOURCE),
                DEF_FONT_BANNER_SYSTEM_NAME,
                DEF_FONT_BANNER_FALLBACK_NAME);
        fntInterfaz = UtilesSwing.generarFuenteRecurso(
                prp.getProperty(PRP_FONT_INTERFACE_RESOURCE),
                DEF_FONT_INTERFACE_SYSTEM_NAME,
                DEF_FONT_INTERFACE_FALLBACK_NAME);

        // Etiqueta de prueba
        lblRotulo = new JLabel();
        lblRotulo.setBackground(Color.WHITE);
        lblRotulo.setBorder(new BevelBorder(BevelBorder.LOWERED));
        lblRotulo.setFont(fntRotulo.deriveFont(Font.PLAIN, 40));
        lblRotulo.setHorizontalAlignment(JLabel.CENTER);
        lblRotulo.setOpaque(true);
        lblRotulo.setText("Érase una vez Java");

        // Selector de Familia
        cbbFamilia = new JComboBox(UtilesSwing.obtenerTipografiasSistema());
        cbbFamilia.setFont(fntInterfaz.deriveFont(Font.PLAIN, 24));
        cbbFamilia.setPreferredSize(new Dimension(400, 40));
        cbbFamilia.setSelectedItem(lblRotulo.getFont().getFamily());

        // Panel de control
        pnlControl = new JPanel(new GridBagLayout());
        pnlControl.add(cbbFamilia);

        // Panel principal
        pnlPpal = new JPanel(new GridLayout(2, 1));
        pnlPpal.add(lblRotulo);
        pnlPpal.add(pnlControl);
        pnlPpal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Ventana principal
        setContentPane(pnlPpal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        try {
            int height = Integer.parseInt(prp.getProperty(PRP_FORM_HEIGHT));
            int width = Integer.parseInt(prp.getProperty(PRP_FORM_WIDTH));
            setSize(width, height);
        } catch (NumberFormatException e) {
            setSize(DEF_FORM_WIDTH, DEF_FORM_HEIGHT);
        }
        setTitle(prp.getProperty(PRP_FORM_TITLE, DEF_FORM_TITLE));
        setLocationRelativeTo(null);
    }

    // Inicialización Anterior    
    private void initBefore() {
        // Establecer LnF
        UtilesSwing.establecerLnFProfile(prp.getProperty(
                PRP_LOOK_AND_FEEL_PROFILE, DEF_LOOK_AND_FEEL_PROFILE));
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(
                PRP_FAVICON_RESOURCE, DEF_FAVICON_RESOURCE));

        // Registrar Festores de Eventos
        cbbFamilia.addActionListener(new AEM(this));
    }

    public void procesarFamilia(ActionEvent e) {
        // Valores Actuales Fuente
        String familia = lblRotulo.getFont().getFamily();
        int estilo = lblRotulo.getFont().getStyle();
        int talla = lblRotulo.getFont().getSize();

        // Familia seleccionada
        familia = (String) cbbFamilia.getSelectedItem();

        // Regenera Familia
        Font fuente = new Font(familia, estilo, talla);

        // Aplica Familia
        lblRotulo.setFont(fuente);
    }
}
