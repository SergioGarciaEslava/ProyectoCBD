package com.gr21.ravenshop.dto;

import com.gr21.ravenshop.model.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerForm {

    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato valido")
    private String email;

    private String phone;

    @Valid
<<<<<<< HEAD
    private AddressForm address = new AddressForm();
=======
    private Address address = new Address();
>>>>>>> featuretask/WI-006-implementar-crud-de-clientes

    public CustomerForm() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressForm getAddress() {
        return address;
    }

    public void setAddress(AddressForm address) {
        this.address = address == null ? new AddressForm() : address;
    }

    public Address toAddress() {
        return new Address(address.getStreet(), address.getCity(), address.getPostalCode());
    }

    public static class AddressForm {

        @NotBlank(message = "La calle es obligatoria")
        private String street;

        @NotBlank(message = "La ciudad es obligatoria")
        private String city;

        @NotBlank(message = "El codigo postal es obligatorio")
        private String postalCode;

        public AddressForm() {
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }
    }
}
