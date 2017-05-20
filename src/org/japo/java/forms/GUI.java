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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import org.japo.java.components.BackgroundPanel;
import org.japo.java.events.AEM;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class GUI extends JFrame {
    // Tamaño de la ventana
    public static final int VENTANA_ANC = 600;
    public static final int VENTANA_ALT = 400;

    // Fuente etiqueta - Parámetros
    private String fntFamLBL;
    public static final int FNT_EST_LBL = Font.PLAIN;
    public static final int FNT_TAM_LBL = 40;
    
    // Fuente Controles - Parámetros
    public static final String FNT_FAM_CTR = "Calibri";
    public static final int FNT_EST_CTR = Font.BOLD;
    public static final int FNT_TAM_CTR = 30;
    
    // Texto de prueba
    public static final String TEXTO = "Érase una vez Java";
    
    // Ruta Imagen de Fondo - Panel Control
    private final String RES_PKG = "/img";
    private final String RES_IMG = "background.jpg";
    private final String RECURSO = RES_PKG + "/" + RES_IMG;

    // Componentes del GUI
    private JLabel lblPrueba;
    private JComboBox<String> cbbFamilia;

    public GUI() {
        // Inicialización PREVIA
        beforeInit();

        // Creación Interfaz
        initComponents();

        // Inicialización POSTERIOR
        afterInit();
    }

    // Construcción del IGU
    private void initComponents() {
        // Fuentes
        Font fntLBL = new Font(fntFamLBL, FNT_EST_LBL, FNT_TAM_LBL);
        Font fntCTR = new Font(FNT_FAM_CTR, FNT_EST_CTR, FNT_TAM_CTR);
        
        // Bordes
        Border brdPNL = new EmptyBorder(10, 10, 10, 10);
        Border brdLBL = new BevelBorder(BevelBorder.LOWERED);
        
        // Tamaños
        Dimension dimFuente = new Dimension(300, 30);
        
        // Color - Etiqueta
        Color c = new Color(184, 244, 244);
        
        // Gestor de Eventos de Cambio
        AEM aem = new AEM(this);
        
        // Etiqueta de prueba
        lblPrueba = new JLabel(TEXTO);
        lblPrueba.setFont(fntLBL);
        lblPrueba.setOpaque(true);
        lblPrueba.setBackground(c);
        lblPrueba.setBorder(brdLBL);
        lblPrueba.setHorizontalAlignment(JLabel.CENTER);

        // Lista de Familias Tipográficas del Sistema
        String[] listaFamilias = GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getAvailableFontFamilyNames();

        // Selector de Familia
        cbbFamilia = new JComboBox(listaFamilias);
        cbbFamilia.setFont(fntCTR);
        cbbFamilia.setPreferredSize(dimFuente);
        cbbFamilia.setSelectedItem(fntLBL);
        cbbFamilia.addActionListener(aem);

        // Imagen de fondo
        URL urlImagen = getClass().getResource(RECURSO);
        Image i = new ImageIcon(urlImagen).getImage();

        // Panel de control
        JPanel pnlControl = new BackgroundPanel(i);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 30, 30);
        pnlControl.setLayout(layout);
        pnlControl.add(cbbFamilia);
        
        // Panel Principal
        JPanel pnlPpal = new JPanel();
        pnlPpal.setLayout(new GridLayout(2, 1));
        pnlPpal.setBorder(brdPNL);
        pnlPpal.add(lblPrueba);
        pnlPpal.add(pnlControl);
        
        // Ventana principal
        setTitle("Selección Familia");
        setContentPane(pnlPpal);
        setSize(VENTANA_ANC, VENTANA_ALT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }

    // Inicialización antes del IGU
    private void beforeInit() {
        fntFamLBL = "Georgia";
    }

    // Inicialización después del IGU
    private void afterInit() {

    }

    public void procesarFamilia(ActionEvent e) {
        // Familia seleccionada
        String familia = (String) cbbFamilia.getSelectedItem();

        // Regenera Familia
        Font f = new Font(familia, FNT_EST_LBL, FNT_TAM_LBL);

        // Aplica Familia
        lblPrueba.setFont(f);
    }
}
