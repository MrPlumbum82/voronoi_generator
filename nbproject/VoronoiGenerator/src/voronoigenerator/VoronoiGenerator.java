package voronoigenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

/**
 * Mistakes that were corrected from previous submission: 
 * 1. Changed the clearButton event handler 
 * 2. Changed the cbox event handler
 *
 * @author sm52192
 * @vesion Apr 24, 2014
 */
public class VoronoiGenerator {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Vornoi Diagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        final VoronoiComponent vc = new VoronoiComponent();
        frame.add(vc);
        vc.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLACK));

        // North Panel
        JPanel distance = new JPanel();
        EtchedBorder eBorder = new EtchedBorder();
        TitledBorder tBorder = new TitledBorder(eBorder, "Distance");
        distance.setBorder(tBorder);
        String[] s = {"Euclidean", "Manhattan"};
        final JComboBox cbox = new JComboBox(s);
        distance.add(cbox);
        frame.add(distance, BorderLayout.NORTH);

        // South Panel
        JPanel clear = new JPanel();
        JButton clearButton = new JButton("Clear");
        clear.add(clearButton);
        frame.add(clear, BorderLayout.SOUTH);

        // Event Handlers
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vc.clear();
            }
        });

        cbox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) cbox.getSelectedItem();
                switch (s) {
                    case "Euclidean":
                        vc.clear();
                        vc.setUseEuclidean(1);
                        break;
                    case "Manhattan":
                        vc.clear();
                        vc.setUseEuclidean(0);
                        break;
                }

//                if (s.equals("Euclidean")) {
//                    vc.setUseEuclidean(true);
//                } else {
//                    vc.setUseEuclidean(false);
//                }
            }

        });

        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }

}
