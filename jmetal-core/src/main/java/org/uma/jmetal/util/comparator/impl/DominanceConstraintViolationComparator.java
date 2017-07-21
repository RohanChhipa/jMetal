package org.uma.jmetal.util.comparator.impl;

import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.comparator.ConstraintViolationComparator;
import org.uma.jmetal.util.solutionattribute.impl.MultiConstraintViolation;

import javax.naming.Context;
import java.util.List;

/**
 * Created by rohan on 2017/07/09.
 */
public class DominanceConstraintViolationComparator implements ConstraintViolationComparator<DoubleSolution>
{
    MultiConstraintViolation multiConstraintViolation;

    public DominanceConstraintViolationComparator()
    {
        multiConstraintViolation = new MultiConstraintViolation();
    }

    @Override
    public int compare(DoubleSolution solution1, DoubleSolution solution2)
    {
        List<Double> a = multiConstraintViolation.getAttribute(solution1);
        List<Double> b = multiConstraintViolation.getAttribute(solution2);

        if (dominates(a, b))
            return -1;

        if (dominates(b, a))
            return 1;

        return 0;
    }

    private boolean dominates(List<Double> a, List<Double> b)
    {
        boolean found = false;

        for (int k = 0; k < a.size(); k++)
        {
            for (int j = 0; j < a.size(); j++)
            {
                if (a.get(k) > b.get(j))
                    return false;

                if (a.get(k) < b.get(k))
                    found = true;
            }
        }

        return found;
    }
}
