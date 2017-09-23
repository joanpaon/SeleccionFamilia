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
import java.awt.GraphicsEnvironment;
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
public class GUI extends JFrame {

    // Propiedades App
    public static final String PRP_LOOK_AND_FEEL = "look_and_feel";
    public static final String PRP_FAVICON = "favicon";

    // Valores por Defecto
    public static final String DEF_LOOK_AND_FEEL = UtilesSwing.LNF_NIMBUS;
    public static final String DEF_FAVICON = "img/favicon.png";

    // Referencias
    private Properties prp;
    private JLabel lblRotulo;
    private JComboBox<String> cbbFamilia;

    // Constructor
    public GUI(Properties prp) {
        // Inicialización Anterior
        initBefore(prp);

        // Creación Interfaz
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción del IGU
    private void initComponents() {
        // Etiqueta de prueba
        lblRotulo = new JLabel();
        lblRotulo.setFont(new Font("Georgia", Font.PLAIN, 40));
        lblRotulo.setText("Érase una vez Java");
        lblRotulo.setHorizontalAlignment(JLabel.CENTER);
        lblRotulo.setBorder(new BevelBorder(BevelBorder.LOWERED));
        lblRotulo.setOpaque(true);
        lblRotulo.setBackground(Color.WHITE);

        // Lista de Familias Tipográficas del Sistema
        String[] listaFamilias = GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getAvailableFontFamilyNames();

        // Selector de Familia
        cbbFamilia = new JComboBox(listaFamilias);
        cbbFamilia.setFont(new Font("Cambria", Font.PLAIN, 24));
        cbbFamilia.setPreferredSize(new Dimension(400, 40));
        cbbFamilia.setSelectedItem(lblRotulo.getFont().getFamily());
        cbbFamilia.addActionListener(new AEM(this));

        // Panel de control
        JPanel pnlControl = new JPanel(new GridBagLayout());
        pnlControl.add(cbbFamilia);

        // Panel principal
        JPanel pnlPpal = new JPanel(new GridLayout(2, 1));
        pnlPpal.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlPpal.add(lblRotulo);
        pnlPpal.add(pnlControl);

        // Ventana principal
        setContentPane(pnlPpal);
        setTitle("Swing Manual #12");
        setResizable(false);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Inicialización Anterior    
    private void initBefore(Properties prp) {
        // Memorizar Referencia
        this.prp = prp;

        // Establecer LnF
        UtilesSwing.establecerLnF(prp.getProperty(PRP_LOOK_AND_FEEL, DEF_LOOK_AND_FEEL));
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(PRP_FAVICON, DEF_FAVICON));
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
