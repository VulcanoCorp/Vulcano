package Controller;

import DAO.RequirementDAO;
import Model.Requirement;

import java.util.ArrayList;

public class RequirementController {

    public ArrayList<Requirement> getAllRequirementsFromProject(int projectId) throws Exception
    {
        try(RequirementDAO requirementDAO = new RequirementDAO())
        {
            return requirementDAO.getAllRequirementsFromProject(projectId);

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Requirement createRequirement(Requirement requirement) throws Exception
    {
        try(RequirementDAO requirementDAO = new RequirementDAO())
        {
            return requirementDAO.createRequirement(requirement);

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void updateRequirement(Requirement requirement) throws Exception
    {
        try(RequirementDAO requirementDAO = new RequirementDAO())
        {
            requirementDAO.updateRequirement(requirement);

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void deleteRequirement(Requirement requirement) throws Exception
    {
        try(RequirementDAO requirementDAO = new RequirementDAO())
        {
            requirementDAO.deleteRequirement(requirement.getId());

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Requirement getRequirement(Requirement requirement) throws Exception
    {
        try(RequirementDAO requirementDAO = new RequirementDAO())
        {
            return requirementDAO.getRequirementById(requirement.getId());

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
