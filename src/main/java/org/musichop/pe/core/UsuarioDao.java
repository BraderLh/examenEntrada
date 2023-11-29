package org.musichop.pe.core;

import org.musichop.pe.domain.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    public boolean addUser(Usuario usuario);
    public boolean deleteUser1(int id);
    public boolean deleteUser2(int id);
    boolean updateUser(String username);
    public void showAllUsers(List<Usuario> usuarios);
    public Usuario getUser(int id);
    public List<Usuario> getAllUsers();
}
