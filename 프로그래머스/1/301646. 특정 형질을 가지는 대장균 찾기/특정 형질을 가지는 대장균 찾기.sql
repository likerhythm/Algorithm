select count(genotype) as COUNT
from ecoli_data
where (genotype & 2 = 0)
AND (genotype & 5 > 0);
