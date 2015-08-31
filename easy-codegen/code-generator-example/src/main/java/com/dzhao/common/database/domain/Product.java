package com.dzhao.common.database.domain;

import com.dzhao.common.codegen.annotation.GenerateDao;
import com.dzhao.common.codegen.annotation.GenerateRepository;

@GenerateDao(
        packageName = "com.dzhao.common.database.dao",
        simpleName = "ProductDao")
@GenerateRepository(
        modelPackageName = "com.dzhao.common.database.domain.*",
        repositoryPackageName = "com.dzhao.common.database.repositories",
        modelSimpleName = "Product",
        simpleName = "ProductRepository")
public class Product {
}
