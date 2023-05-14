package Assignment3.presentationLayer.controllers;

import Assignment3.businessLayer.ClientsBLL;
import Assignment3.modelLayer.Client;
import Assignment3.presentationLayer.views.clients.ClientsView;
import Assignment3.presentationLayer.views.clients.ClientsView2;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Field;
import java.util.List;

/**
 *  This class controls the two client views. It calls methods that communicate with and modify the Client table from
 *  the database as well as methods that communicate with the user through the interface
 *  @author Tudor Cristea
 */
public class ClientsController
{
    /**
     * The business logic layer of the client
     */
    private ClientsBLL clientsBLL;

    /**
     * The first client view (part of the GUI)
     */
    private ClientsView clientsView;
    /**
     * The second client view (part of the GUI)
     */
    private ClientsView2 clientsView2;

    /**
     * The client to be deleted/updated
     */
    private Client client;

    /**
     * The initial name of the client - in case of adding a client, it will be an empty string
     *                                - in case of editing a client, it will be the name of that client
     */
    private String initialName;
    /**
     * The initial phone number of the client - in case of adding a client, it will be an empty string
     *                                        - in case of editing a client, it will be the phone number of that client
     */
    private String initialPhoneNumber;

    /**
     * The windowListener of the first client view
     */
    private WindowListener windowListener;

    /**
     * This is the only constructor of this class. It assures the "connection" between the presentation layer and the
     * business logic layer for the Client model
     * @param clientsView the first Client view object
     */
    public ClientsController(ClientsView clientsView)
    {
        this.clientsView = clientsView;
        this.clientsBLL = new ClientsBLL();

        windowListener = new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e)
            {
                clientsView.setEnabled(false);
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                clientsView.setEnabled(true);
                List<Client> clientList = null;
                try
                {
                    clientList = clientsBLL.findAllClients();
                }
                catch (Exception ex)
                {
                    clientsView.showErrorMessage(ex.getMessage());
                }
                Field[] fields = Client.class.getDeclaredFields();
                String[] headerTitles = new String[fields.length];
                for (int i = 0; i < fields.length; ++i)
                {
                    headerTitles[i] = fields[i].getName();
                }
                clientsView.getClientsTable().setModel(new DefaultTableModel(headerTitles, 0));
                clientsView.getClientsComboBox().removeAllItems();

                for (Client client: clientList)
                {
                    ((DefaultTableModel) clientsView.getClientsTable().getModel()).addRow(new String[]{String.valueOf(client.getId()), client.getName(), client.getPhoneNumber()});
                    clientsView.getClientsComboBox().addItem(client.getName());
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        };

        this.clientsView.addAddClientButtonListener(new AddClientButtonListener());
        this.clientsView.addEditClientButtonListener(new EditClientButtonListener());
        this.clientsView.addDeleteClientButtonListener(new DeleteClientButtonListener());
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Reset Name Button
     */
    class ResetNameButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and changes the Name Text Field to the initial
         * name of the client (the one that was displayed when the window opened)
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            clientsView2.getNameTextField().setText(initialName);
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Reset Phone Number Button
     */
    class ResetPhoneNumberButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and changes the Phone Number Text Field to the
         * initial phone number of the client (the one that was displayed when the window opened)
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            clientsView2.getPhoneNumberTextField().setText(initialPhoneNumber);
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Add Button from the second Client View
     */
    class AddButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and adds a new client to the database or
         * displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String name = clientsView2.getNameTextField().getText();
            String phoneNumber = clientsView2.getPhoneNumberTextField().getText();
            try
            {
                clientsBLL.insertClient(name, phoneNumber);
                clientsView2.showMessage("Client added successfully!");
            }
            catch (Exception ex)
            {
                clientsView2.showErrorMessage(ex.getMessage());
            }
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Edit Button from the second Client View
     */
    class EditButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and edits an existing client from the database
         * or displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String name = clientsView2.getNameTextField().getText();
            String phoneNumber = clientsView2.getPhoneNumberTextField().getText();

            try
            {
                clientsBLL.updateClient(client, name, phoneNumber);
                clientsView2.showMessage("Client updated successfully!");
            }
            catch (Exception ex)
            {
                clientsView2.showErrorMessage(ex.getMessage());
            }
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Delete Button from the second Client View
     */
    class DeleteButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and deletes an existing client from the database
         * or displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                clientsBLL.deleteClient(client);
                clientsView2.showMessage("Client deleted successfully!");
            }
            catch (Exception ex)
            {
                clientsView2.showErrorMessage(ex.getMessage());
            }
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Add Button from the first Client View
     */
    class AddClientButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and opens the second Client View so that the
         * user can enter the parameters for a new client to be created or displays a prompt containing the error
         * message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            clientsView2 = new ClientsView2(windowListener);
            clientsView2.getEditClientButton().setEnabled(false);
            clientsView2.getDeleteClientButton().setEnabled(false);
            clientsView2.getTitleLabel().setText("<html><div style='text-align: center;'>Add<br>Client</div></html>");
            clientsView2.addResetNameButtonListener(new ResetNameButtonListener());
            clientsView2.addResetPhoneNumberButtonListener(new ResetPhoneNumberButtonListener());
            clientsView2.addAddClientButtonListener(new AddButtonListener());

            initialName = clientsView2.getNameTextField().getText();
            initialPhoneNumber = clientsView2.getPhoneNumberTextField().getText();
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Edit Button from the first Client View
     */
    class EditClientButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and opens the second Client View so that the
         * user can modify the parameters of an existing client to be edited (which was selected by the user) or
         * displays a prompt containing the error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            clientsView2 = new ClientsView2(windowListener);
            clientsView2.getAddClientButton().setEnabled(false);
            clientsView2.getDeleteClientButton().setEnabled(false);
            clientsView2.getTitleLabel().setText("<html><div style='text-align: center;'>Edit<br>Client</div></html>");
            clientsView2.addResetNameButtonListener(new ResetNameButtonListener());
            clientsView2.addResetPhoneNumberButtonListener(new ResetPhoneNumberButtonListener());
            clientsView2.addEditClientButtonListener(new EditButtonListener());

            try
            {
                client = clientsBLL.findClientByName((String) clientsView.getClientsComboBox().getSelectedItem());
                clientsView2.getNameTextField().setText(client.getName());
                clientsView2.getPhoneNumberTextField().setText(client.getPhoneNumber());
            }
            catch (Exception ex)
            {
                clientsView2.showErrorMessage(ex.getMessage());
            }

            initialName = clientsView2.getNameTextField().getText();
            initialPhoneNumber = clientsView2.getPhoneNumberTextField().getText();
        }
    }

    /**
     * This inner class implements the "ActionListener" interface which will be added as an Action Listener to the
     * Delete Button from the first Client View
     */
    class DeleteClientButtonListener implements ActionListener
    {
        /**
         * This method overrides the original "actionPerformed" method and opens the second Client View so that the
         * user can delete an existing client (which was selected by the user) or displays a prompt containing the
         * error message, if it is not possible to do so
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            clientsView2 = new ClientsView2(windowListener);
            clientsView2.getAddClientButton().setEnabled(false);
            clientsView2.getEditClientButton().setEnabled(false);
            clientsView2.getResetNameButton().setEnabled(false);
            clientsView2.getResetPhoneNumberButton().setEnabled(false);
            clientsView2.getNameTextField().setEnabled(false);
            clientsView2.getPhoneNumberTextField().setEnabled(false);
            clientsView2.getTitleLabel().setText("<html><div style='text-align: center;'>Delete<br>Client</div></html>");
            clientsView2.addDeleteClientButtonListener(new DeleteButtonListener());

            try
            {
                client = clientsBLL.findClientByName((String) clientsView.getClientsComboBox().getSelectedItem());
                clientsView2.getNameTextField().setText(client.getName());
                clientsView2.getPhoneNumberTextField().setText(client.getPhoneNumber());
            }
            catch (Exception ex)
            {
                clientsView2.showErrorMessage(ex.getMessage());
            }
        }
    }
}